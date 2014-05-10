'''
Created on May 10, 2014

@author: hardikarora
'''

import avro.protocol
import avro.ipc as ipc

class AvroConnection(object):
    ''' Avro connection class to get connection to server'''
    def __init__(self, server_address, port, json_protocol):
        self.client = ipc.HTTPTransceiver(server_address, port)
        self.requestor = ipc.Requestor(json_protocol, self.client)
    
    def call_method(self, method_name, argument=None):
        if argument is not None:
            return_value = self.requestor.request(method_name, argument)
            return return_value
        return_value = self.requestor.request(method_name)
        return return_value
    
    def __delattr__(self, *args, **kwargs):
        self.client.close()
        return object.__delattr__(self, *args, **kwargs)
    
if __name__== "__main__":
    avr_file_name = "student_schema.avr"
    server_name = "localhost"
    server_port = 9090
    json_protocol = avro.protocol.parse(open(avr_file_name).read())
    connection = AvroConnection(server_name , server_port, json_protocol)
    student =   {"firstName" : "hardik" ,\
                "lastName" : "arora" ,\
                 "age" : 23, \
                 "major" :"COMPUTER_SCIENCE"
                }
    
    parameter = {"student" : student}
    
    method_name = "getStudentInformation"
    
    value = connection.call_method(method_name , parameter)
    print str(value)
