class ThreadBase(object):
    def __init__(self, thread_name, application_terminate_event, sleep_interval):
        super(ThreadBase, self).__init__()
        self.thread_name = thread_name
        self.application_terminate_event = application_terminate_event
        self.sleep_interval = sleep_interval

    def get_thread_name(self):
        return self.thread_name

    def get_sleep_interval(self):
        return self.sleep_interval

    def get_application_terminate_event(self):
        return self.application_terminate_event
