https://blog.csdn.net/oHuaXin1234/article/details/107115030?utm_medium=distribute.pc_relevant.none-task-blog-title-6&spm=1001.2101.3001.4242


执行：pytest

生成allure格式结果：//pytest -s -q --alluredir report

pytest --alluredir report/result

生成allure报告： //allure generate directory-with-results/ -o directory-with-report

allure generate report/result -o report/allure_html --clean