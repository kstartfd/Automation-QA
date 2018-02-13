from selenium.webdriver.common.by import By


class MainPageLocators(object):

    NAME = (By.NAME, "login")
    PASSWORD = (By.NAME, "password")
    BUTTON_SUBMIT = (By.XPATH, "//button[@type='submit']")


class OnMatchingLocators(object):

    ALL = (By.XPATH, "//li[@id='savedfilter233']/a")
    ON_MATCHING = (By.XPATH, "//li[@id='savedfilter215']/a")
    ON_MATCHING_TH = (By.XPATH, "//th[@data-field='Coordinators' and @data-title='Согласующие']")
    TABLE = (By.XPATH, "//div[@id='tableholder']")
    TR = (By.TAG_NAME, "tr")
    NAME_OF_TD = (By.XPATH, "//td[@class='taskname task-name']/a[@class='openlink']")

