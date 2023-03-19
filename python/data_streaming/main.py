import sys
from src.service.consumer_service import ConsumerService

if __name__ == '__main__':
    if len(sys.argv) <= 1:
        print ("application config file name parameter is missing. message consumer exit.")
        exit(0)

    application_config_filename = sys.argv[1]
    ConsumerService(application_config_filename).start_consumer()
    print("main terminates...")
