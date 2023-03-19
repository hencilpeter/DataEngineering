import os


class FileUtil:
    def __init__(self):
        pass

    def is_file_exist(self, filenane):
        is_exist = False
        try:
            is_exist = os.path.exists(filenane)
        except Exception as ex:
            print("Exception raised from function {} : {}".format(__name__, str(ex)))
        return is_exist

    def delete_file(self, filename):
        is_deleted = False
        try:
            if self.is_file_exist(filename):
                os.remove(filename)
                is_deleted = True
        except Exception as ex:
            print("Exception raised from function {} : {}".format(__name__, str(ex)))

        return is_deleted

    def create_empty_file(self, filename):
        # TODO--reformat exception handling
        try:
            with open(filename, "w"):
                pass
        # except FileNotFoundError as file_not_found_exception:
        #     print(str(file_not_found_exception))
        except Exception as exception:
            print("exception raised while creating file: {}".format(str(exception)))
