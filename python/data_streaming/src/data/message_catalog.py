# TODO: implement the Message Catalog (use SqLight ? )
import sqlite3


class MessageCatalog:
    def __init__(self, is_memory=True, db_file_name=""):
        self.catalog_connection = sqlite3.connect(':memory:')
        self.max_count = 99

    def add_message(self):
        pass

    def remove_message(self):
        pass

    def is_empty(self):
        return self.catalog_connection.total_changes() == 0

    def is_full(self):
        return self.catalog_connection.total_changes() >= self.max_count

    def clean(self):
        pass

    def get_message_count(self):
        pass
