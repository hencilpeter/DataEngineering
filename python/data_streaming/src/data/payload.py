class Payload:
    def __init__(self, dict_message):
        # self.dict = json.loads (message)
        self.dict = dict_message

    def get_value(self, tag_list):
        data = self.dict
        for tag in tag_list:
            data = data[tag]
        return data

    def get_value_with_key(self, tag_list, key_index):
        value = self.get_value(tag_list=tag_list)
        if key_index <= -1:
            return value
        list_value = list(value)
        return list_value[key_index]

    def get_value_from_payload_tag(self, json_tag_name):
        pipe_symbol_index = json_tag_name.find("|")
        if pipe_symbol_index == -1:
            temp_list = json_tag_name.split(",")
            return self.get_value(tag_list=temp_list)

        key_index = int(json_tag_name[pipe_symbol_index + 1:])
        tag_list = str(json_tag_name[:pipe_symbol_index]).split(",")
        return self.get_value_with_key(tag_list=tag_list, key_index=key_index)
