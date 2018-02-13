import os
import unittest

from selenium import webdriver

from TestCases import test_cases
from data.ListUsers import users

from pom.Pages import MainPage, OnMatching


class TestPages(unittest.TestCase):
    def setUp(self):
        dir = os.path.dirname(__file__)
        chrome_driver_path = dir + "\chromedriver.exe"
        self.driver = webdriver.Chrome(chrome_driver_path)
        self.driver.maximize_window()
        self.driver.implicitly_wait(30)
        self.driver.get("http://172.20.0.85:88/")

    def test_page_load(self):

        print("\n" + str(test_cases(0)))
        page = MainPage(self.driver)

        page.enter_page()
        self.assertTrue(page.check_page_loaded())

        print("\n" + str(test_cases(1)))

        page.search_item_and_input_data("kostykevich_a", "12311231")

        intraPage = page.click_sign_up()

        intraPage.open_link_on_matching()


    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    suite = unittest.TestLoader().loadTestsFromTestCase(TestPages)
    unittest.TextTestRunner(verbosity=2).run(suite)
