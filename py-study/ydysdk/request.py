class Request:
    method = 'POST'

    def __init__(self):
        pass

    def get_params(self):
        pass


class NodeGetRequest(Request):
    node_path = None
    api = "/get"

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
