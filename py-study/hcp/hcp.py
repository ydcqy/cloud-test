import json
import logging
import re
import time

import requests
from colorama import init, Fore, Back
from prettytable import PrettyTable


class Colored(object):
    def __init__(self):
        init(autoreset=True)

    #  前景色:红色  背景色:默认
    def red(self, s):
        return Fore.RED + s + Fore.RESET

    #  前景色:绿色  背景色:默认
    def green(self, s):
        return Fore.GREEN + s + Fore.RESET

    #  前景色:黄色  背景色:默认
    def yellow(self, s):
        return Fore.YELLOW + s + Fore.RESET

    #  前景色:蓝色  背景色:默认
    def blue(self, s):
        return Fore.BLUE + s + Fore.RESET

    #  前景色:洋红色  背景色:默认
    def magenta(self, s):
        return Fore.MAGENTA + s + Fore.RESET

    #  前景色:青色  背景色:默认
    def cyan(self, s):
        return Fore.CYAN + s + Fore.RESET

    #  前景色:白色  背景色:默认
    def white(self, s):
        return Fore.WHITE + s + Fore.RESET

    #  前景色:黑色  背景色:默认
    def black(self, s):
        return Fore.BLACK

    #  前景色:白色  背景色:绿色
    def white_green(self, s):
        return Fore.WHITE + Back.GREEN + s + Fore.RESET + Back.RESET


color = Colored()


def is_valid_date(str):
    """判断是否是一个有效的日期字符串"""
    try:
        time.strptime(str, "%Y-%m-%d")
        return True
    except ValueError:
        return False


# 获取所有车站的编码字典
def get_station_dict():
    url = 'https://kyfw.12306.cn/otn/resources/js/framework/station_name.js?station_version=1.9018'
    reponse = requests.get(url)
    pattern = u'([\u4e00-\u9fa5]+)\|([A-Z]+)'
    result = re.findall(pattern, reponse.text)
    rs = {}
    rs.update(dict((y, x) for x, y in result))
    rs.update(dict((x, y) for x, y in result))
    return rs


def query_ticket(from_station, to_station, train_date):
    url = "https://kyfw.12306.cn/otn/leftTicket/queryX?leftTicketDTO.train_date=" + train_date + "&leftTicketDTO.from_station=" + from_station + "&leftTicketDTO.to_station=" + to_station + "&purpose_codes=ADULT"
    response = requests.get(url)
    response.encoding = "utf-8"
    type_ = response.headers["Content-Type"]
    if "application/json" not in type_:
        raise TypeError("结果未知")
    return response.text


def analyze_query_ticket_result(raw_trains, code_dict):
    tb = PrettyTable()  # 实例化一个对象
    tb.field_names = ["车次", "出发站", "目的地", "出发时间", "到达时间", "消耗时间", "一等座", "二等座", "软卧", "硬卧", "硬座", "无座"]  # 创造需要的字段
    try:
        for raw_train in raw_trains:  # 循环遍历每辆列车的信息
            train_list = []  # 一趟火车的全部信息
            data_list = raw_train.split('|')
            train_no = data_list[3]  # 车次号码
            train_list.append(train_no)
            from_station_code = data_list[6]  # 出发站
            from_station_name = code_dict[from_station_code]  # 将城市代码 替换成城市名字
            train_list.append(from_station_name)
            to_station_code = data_list[7]  # 终点站
            to_station_name = code_dict[to_station_code]  # 将城市代码 替换成城市名字
            train_list.append(to_station_name)
            start_time = data_list[8]  # 出发时间
            train_list.append(start_time)
            arrive_time = data_list[9]  # 到达时间
            train_list.append(arrive_time)
            lishi = data_list[10]  # 历时
            train_list.append(lishi)
            first_class_seat = data_list[31] or '--'  # 一等座
            train_list.append(first_class_seat)
            second_class_seat = data_list[30] or '--'  # 二等座
            train_list.append(second_class_seat)
            soft_sleep = data_list[23] or '--'  # 软卧
            train_list.append(soft_sleep)
            hard_sleep = data_list[28] or '--'  # 硬卧
            train_list.append(hard_sleep)
            hard_seat = data_list[29] or '--'  # 硬座
            train_list.append(hard_seat)
            no_seat = data_list[26] or '--'  # 无座
            train_list.append(no_seat)
            tb.add_row(train_list)  # 以补充行的形式来添加数据.
        print(tb)  # 打印查询结果
    except:
        print('输入信息有误，请重新输入')


logging.basicConfig(level=logging.INFO,
                    format='%(asctime)s %(levelname)s %(thread)d --- [%(threadName)s] %(module)s.%(funcName)s : %(message)s'
                    )

if __name__ == '__main__':
    print(color.cyan('''
**********************************************
************* 请输入查询条件 *****************
**********************************************
    '''))
    sd = get_station_dict()
    print("出发地: ", end="")
    fromStation = input()
    while sd.get(fromStation) is None:
        print("站点不存在，请重输！")
        print("出发地: ", end="")
        fromStation = input()
    print("目的地: ", end="")
    toStation = input()
    while sd.get(toStation) is None:
        print("站点不存在，请重输！")
        print("目的地: ", end="")
        toStation = input()
    print("出发日(格式: 2019-05-01): ", end="")
    trainDate = input()
    while not is_valid_date(trainDate):
        print("日期格式不正确，请重输！")
        print("出发日(如: 2019-05-01): ", end="")
        trainDate = input()
    s = query_ticket(sd[fromStation], sd[toStation], trainDate)
    tickets = json.loads(s)['data']['result']
    analyze_query_ticket_result(tickets, sd)
