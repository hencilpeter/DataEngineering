class PayloadTagName:
    OFFSET_ID = ""
    TOPIC_NAME = ""
    TABLE_NAME = "headers|0"
    OPERATION = "headers,<<table_name>>,operation"
    TIMESTAMP = "headers,<<table_name>>,timestamp"
    DATA = "data"

    def __init__(self):
        pass
