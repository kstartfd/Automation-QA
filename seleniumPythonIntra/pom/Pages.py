import time
from selenium.common.exceptions import NoSuchElementException

from pom.Base import Page
from pom.Locators import MainPageLocators, OnMatchingLocators


class MainPage(Page):
    def enter_page(self):
        self.shell()

    def check_page_loaded(self):
        return True if self.find_element(*MainPageLocators.NAME) else False

    def search_item_and_input_data(self, user, password):
        self.find_element(*MainPageLocators.NAME).send_keys(user)
        self.find_element(*MainPageLocators.PASSWORD).send_keys(password)

    def click_sign_up(self):
        element = self.find_element(*MainPageLocators.BUTTON_SUBMIT)
        element.click()
        return OnMatching(self.driver)


class OnMatching(Page):

    def open_link_on_matching(self):

        try:
            self.find_element(*OnMatchingLocators.ALL).click()
            time.sleep(5)
            self.find_element(*OnMatchingLocators.ON_MATCHING).click()
            time.sleep(5)
            self.find_element(*OnMatchingLocators.ON_MATCHING_TH).click()

            table = self.driver.find_element(*OnMatchingLocators.TABLE)

            rows = table.find_elements(*OnMatchingLocators.TR)  # get all of the rows in the table

            for row in rows:
                result = row.find_elements(*OnMatchingLocators.NAME_OF_TD)
                for each in result:
                    print("Attribute ", each.get_attribute('href'))

        except NoSuchElementException:
            return False
        return True

