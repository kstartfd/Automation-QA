import win32com.client
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.action_chains import ActionChains


# this Base class is serving basic attributes for every single page inherited from Page class

class Page(object):

    def __init__(self, driver, base_url='http://172.20.0.85:88/'):
        self.base_url = base_url
        self.driver = driver
        self.timeout = 30

    def shell(self):

        self.shell = win32com.client.Dispatch("WScript.Shell")
        self.shell.Sendkeys("kostykevich_a")
        self.shell.Sendkeys("{TAB}")
        self.shell.Sendkeys("12311231")
        self.shell.Sendkeys("{ENTER}")

    def find_element(self, *locator):
        return self.driver.find_element(*locator)

    def open(self, url):
        url = self.base_url + url
        self.driver.get(url)

    def get_title(self):
        return self.driver.title

    def get_url(self):
        return self.driver.current_url

    def hover(self, *locator):
        element = self.find_element(*locator)
        hover = ActionChains(self.driver).move_to_element(element)
        hover.perform()






