import os

from module_config_manager import config_manager
from module_extractor import extractor
from module_loader import loader
from module_postal_data_provider import postal_data_provider
from module_transformer import transformer

if __name__ == "__main__":
    # load configurations and postal data information
    config = config_manager()
    postal_data_provider = postal_data_provider(config.get_value("POSTAL_INFORMATION_FILE"))

    print("etl process started....")
    # 1. extract phase
    extractor = extractor()
    account_information_data = extractor.get_source_data(config.get_value("ACCOUNT_INFORMATION_FILE"))
    sales_revenue_data = extractor.get_source_data(config.get_value("SALES_REVENUE_FILE"))
    print("1/3 exraction completed.")

    # 2. transformation phase
    transformer = transformer(config.get_value("POSTAL_INFORMATION_FILE"))
    account_information_data_transformed = transformer.get_transformed_data(account_information_data,
                                                                            postal_data_provider)
    print("2/3 transformation completed.")
    # pd.set_option('display.max_columns', None)
    # print(account_information_data_transformed)

    # 3. load phase - no DB load is required here
    # save the two reports
    loader = loader()
    loader.save_customer_distribution_report(account_information_data_transformed,
                                             os.path.join(config.get_value("REPORT_PATH"),
                                                          config.get_value("DISTRIBUTION_REPORT_FILE_NAME")))
    loader.save_top_customer_contact_information_report(sales_revenue_data, account_information_data_transformed,
                                                        os.path.join(config.get_value("REPORT_PATH"),
                                                                     config.get_value("TOP_CUSTOMER_REPORT_FILE_NAME")))
    print("3/3 load/save completed.")
    print("etl process completed....")