from twisted.internet import reactor
from twisted.internet import protocol
from twisted.protocols.basic import LineReceiver

PORT = 8000

class DataProtocol(LineReceiver):
    def __init__(self,factory):
        self.factory = factory
        self.state = "HANDLE"

    def connectionMade(self):
        self.sendLine("Whats your name ?")

    def lineReceived(self,line):
        if self.state=="HANDLE":
            self.register(line)
        else:
            self.chat(line)
            
    def broadcast(self,message):
        for name, protocol_instance in self.factory.names.items():
            if protocol_instance != self: 
                protocol_instance.sendLine(message)

    def connectionLost(self,reason):
        if self in self.factory.names.values():
            del(self.factory.names[self.name])
        print "exiting"
    
    
    def register(self, line):
        if line in self.factory.names:
            self.sendLine("Enter another name")
            return
        self.sendLine("Welcome {}".format(line))
        self.factory.names[line] = self
        self.broadcast("{} has joined the group".format(line))
        self.name = line
        self.state ="CHAT"
        

    def chat(self,line):
        message = "<{}> :{} ".format(self.name,line)  
        self.broadcast(message)

    


class ChatFactory(protocol.Factory):

    def __init__(self):
        self.names = {}

    def buildProtocol(self,addr):
        return DataProtocol(self)

print "Listening at %d " % (PORT,)

reactor.listenTCP(PORT,ChatFactory())
reactor.run()

