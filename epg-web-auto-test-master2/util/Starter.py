#!/usr/bin/env python
# -*- encoding: utf-8 -*-
'''
@文件        :Starter3.py
@说明        : 启动器，用于启动应用或者网页，获取全局的driver
@时间        :2020/09/15 16:01:04
@作者        :GongCheng
'''

import sys
sys.path.append("/Users/gaoyuhang/Project/epg-web-auto-test-master")

from threading import Thread, Lock
from selenium import webdriver
from config.config import config as con

from config.Constants import const
import os
import time


class Starter():
    mutex = Lock()
    driver = None

    def initDriver(self):
        options = webdriver.ChromeOptions()
        if con.PLATFORM == con.Electron:
            options.binary_location = con.APP_FILE_PATH
            launchArgument = '--protocol https --env ' + con.ENV
            options.add_argument(launchArgument)

        elif con.PLATFORM == con.Standard:
            print("Standard Mode")

        else:
            print("error2")

        if con.BROWER == con.Chrome:
            path = os.path.abspath("./") + "/driver/chromedriver"
            driver = webdriver.Chrome(executable_path=path, options=options)
        else:
            print("error3")

        self.driver = driver

    def getDriver(self):
        if self.driver is None:
            self.mutex.acquire()
            self.initDriver()
            self.mutex.release()
            return self.driver
        else:
            return self.driver

    def run(self, url = None):
        if self.driver is not None:
            if con.PLATFORM == con.Standard:
                self.driver.get(url)
            else:
                print("URL is None")
        else:
            self.initDriver()
            print("initDriver")

    def quit(self):
        if self.driver is not None:
            self.driver.quit()

    '''根据平台及版本，初始化webdriver'''

starter = Starter()

if __name__ == '__main__':

    Starter().run()

