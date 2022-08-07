
import pandas as pd

class loader:
    def save_customer_distribution_report(self, account_information_data_transformed, report_file_name):
        try:
            #filter records with valid postal code
            filtered_account_information_data = account_information_data_transformed[account_information_data_transformed["is_valid_postal_code"] == True]
            #group the data
            customer_distribution_report_data = filtered_account_information_data.groupby(["sector", "location"], sort=False)["postal_code"].count().reset_index()
            #rename columns
            customer_distribution_report_data.columns = ["customer_postal_sector", "customer_location", "customer_count"]
            #save report
            customer_distribution_report_data.sort_values(by="customer_postal_sector").to_csv(report_file_name, sep=',', encoding='utf-8', index=False)
        except Exception as exception:
            print("Exception raised from function : save_customer_distribution_report")
            traceback.print_exc()

    def save_top_customer_contact_information_report(self, account_information_data_transformed, account_information_data, report_file_name, customer_count=10, sales_revenue_period=20171201):
        try:
            # filter records for the given revenue period
            filtered_customer_data = account_information_data_transformed[account_information_data_transformed["sales_revenue_period_calendar_sid"] == sales_revenue_period]

            # group the records by sales revenue
            customer_revenue_data = filtered_customer_data.groupby(["account_id", "sales_revenue_period_calendar_sid"])["sales_revenue_before_tax_usd_amount"].sum().reset_index()

            customer_revenue_data = customer_revenue_data.sort_values(by="sales_revenue_before_tax_usd_amount", ascending=False).head(10)
            customer_revenue_data.columns = ["customer_id", "sales_month" , "sales_revenue"]

            top_customer_details = customer_revenue_data.join(account_information_data,
                                        how="left", lsuffix="l_", rsuffix='r_')[[
             "customer_id" , "postal_code", "account_registration_address", "account_registration_phone_number", "sales_month", "sales_revenue"]]

            #rename columns
            top_customer_details.columns = ["customer_id" , "customer_postal_code", "customer_general_location", "customer_phone_number", "sales_month", "sales_revenue"]

            # save report
            top_customer_details.to_csv(report_file_name, sep=',', encoding='utf-8', index=False)
        except Exception as exception:
            print("Exception raised from function : save_top_customer_contact_information_report")
            traceback.print_exc()

