config.pyconfig.pyimport logging
import telnetlib
import time


class TelnetClient:
    def __init__(self, host, password, commands):
        self.tn = telnetlib.Telnet()
        self.host = host
        self.password = password
        self.commands = commands

    # 此函数实现telnet登录操作
    def login_host(self):
        try:
            self.tn.open(self.host, port=23)
        except:
            logging.warning('%s网络连接失败' % self.host)
            return False

        # 等待Password出现后输入用户名，最多等待10秒
        self.tn.read_until(b'Password: ', timeout=10)
        self.tn.write(self.password.encode('ascii') + b'\n')

        # 延时两秒再收取返回结果，给服务端足够响应时间
        time.sleep(2)
        # 获取登录结果
        # read_very_eager()获取到的是的是上次获取之后本次获取之前的所有输出
        command_result = self.tn.read_very_eager().decode('ascii')
        print(command_result)
        if 'Password: ' not in command_result:
            logging.warning('%s登录成功' % self.host)
            return True
        else:
            logging.warning('%s登录失败，用户名或密码错误' % self.host)
            return False

    # 此函数实现执行传过来的命令，并输出其执行结果
    def execute_some_command(self):
        # # 执行命令
        # self.tn.write(command.encode('ascii') + b'\n')
        # time.sleep(2)
        # # 获取命令结果
        # command_result = self.tn.read_very_eager().decode('ascii')
        # logging.warning('命令执行结果：\n%s' % command_result)
        # self.tn.read_until(b'Password: ', timeout=10)
        # self.tn.write(password.encode('ascii') + b'\n')
        # time.sleep(2)
        # # 获取命令结果
        # command_result = self.tn.read_very_eager().decode('ascii')
        # logging.warning('命令执行结果：\n%s' % command_result)
        # 进入特权模式后再执行命令组
        self.tn.write('en'.encode('ascii') + b'\n')
        time.sleep(2)
        self.tn.read_until(b'Password: ', timeout=10)
        self.tn.write(password.encode('ascii') + b'\n')
        time.sleep(2)
        self.tn.read_until(b'Router#', timeout=10)
        for command in self.commands:
            self.tn.write(command.encode('ascii') + b'\n')
            time.sleep(2)

    # 退出telnet
    def logout_host(self):
        self.tn.write(b"exit\n")


if __name__ == '__main__':
    host1 = '172.16.0.2'
    host2 = '172.16.0.3'
    host3 = '172.16.0.4'
    password = 'cisco'
    commands_R1 = ['conf t',
                   'int f0/1',
                   'ip address 192.168.10.1 255.255.255.0',
                   'no shutdown',
                   'int s0/0/0',
                   'ip address 192.168.20.1 255.255.255.0',
                   'clock rate 9600',
                   'no shutdown',
                   'exit',
                   'router rip',
                   'network 192.168.10.0',
                   'network 192.168.20.0',
                   'exit']

    commands_R2 = ['conf t',
                   'int s0/0/0',
                   'ip address 192.168.20.2 255.255.255.0',
                   'clock rate 9600',
                   'no shutdown',
                   'int s0/0/1',
                   'ip address 192.168.30.1 255.255.255.0',
                   'clock rate 9600',
                   'no shutdown',
                   'exit',
                   'router rip',
                   'network 192.168.20.0',
                   'network 192.168.30.0',
                   'exit']

    commands_R3 = ['conf t',
                   'int f0/1',
                   'ip address 192.168.40.1 255.255.255.0',
                   'clock rate 9600',
                   'no shutdown',
                   'int s0/0/0',
                   'ip address 192.168.30.2 255.255.255.0',
                   'clock rate 9600',
                   'no shutdown',
                   'exit',
                   'router rip',
                   'network 192.168.30.0',
                   'network 192.168.40.0',
                   'exit']

    R1 = TelnetClient(host1, password, commands_R1)
    if R1.login_host():
        R1.execute_some_command()
        R1.logout_host()

    R2 = TelnetClient(host2, password, commands_R2)
    if R2.login_host():
        R2.execute_some_command()
        R2.logout_host()

    R3 = TelnetClient(host3, password, commands_R3)
    if R3.login_host():
        R3.execute_some_command()
        R3.logout_host()
