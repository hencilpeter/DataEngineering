class ApplicationConfigurationName:
    COMMON_SECTION = "common"
    APPLICATION_NAME = "application_name"
    APPLICATION_TICKET_ID_FILE_EXTENSION = "application_ticket_id_file_extension"
    HEARTBEAT_FOLDER_PATH = "heartbeat_folder_path"
    RIP_REQUEST_FOLDER_PATH = "rip_request_folder_path"
    LOG_PATH = "log_path"
    THREAD_CONFIG_FILE_NAME = "thread_config_file_name"
    TABLES_CONFIG_FILE_NAME = "tables_config-file_name"
    RUNNING_MODE = "running_mode"

    KAFKA_SECTION = "kafka"
    BOOTSTRAP_SERVER = "bootstrap_server"
    TOPIC_NAME = "topic_name"
    AUTO_OFFSET_RESET = "auto_offset_reset"
    ENABLE_AUTO_COMMIT = "enable_auto_commit"

    def __init__(self):
        pass
