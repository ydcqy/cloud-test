import json


class Request:
    method = 'POST'
    api = None

    def __init__(self):
        pass

    def get_params(self):
        pass


class NodeGetRequest(Request):
    api = "/get"

    node_path = None

    def __init__(self):
        pass

    def set_node_path(self, node_path):
        self.node_path = node_path

    def get_params(self):
        params = {
            "nodePath": self.node_path,
            "api": self.api
        }
        return str(params)


class NodeAddRequest(Request):
    api = "/add"
    node_path = None
    node_content = None

    def __init__(self):
        pass

    def set_node_path(self, node_path):
        self.node_path = node_path

    def set_node_content(self, node_content):
        self.node_content = node_content

    def get_params(self):
        params = {
            "nodePath": self.node_path,
            "nodeContent": self.node_content,
            "api": self.api
        }
        return json.dumps(params)


class NodeUpdateRequest(Request):
    api = "/update"
    node_path = None
    node_content = None

    def __init__(self):
        pass

    def set_node_path(self, node_path):
        self.node_path = node_path

    def set_node_content(self, node_content):
        self.node_content = node_content

    def get_params(self):
        params = {
            "nodePath": self.node_path,
            "nodeContent": self.node_content,
            "api": self.api
        }
        return json.dumps(params)


class NodeDeleteRequest(Request):
    api = "/delete"
    node_path = None

    def __init__(self):
        pass

    def set_node_path(self, node_path):
        self.node_path = node_path

    def get_params(self):
        params = {
            "nodePath": self.node_path,
            "api": self.api
        }
        return json.dumps(params)


class NodeWatchRequest(Request):
    api = "/get"

    node_path = None

    def __init__(self):
        pass

    def set_node_path(self, node_path):
        self.node_path = node_path

    def get_params(self):
        params = {
            "nodePath": self.node_path,
            "api": self.api
        }
        return str(params)
