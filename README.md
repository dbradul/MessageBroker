MessageBroker
=============

The project provides functionality for asynchronous communication using messages in JSON-like format.

The communication between server and client is implemented by sending and receiving messages via common data bus - 
MessageBroker. The GCF format (Generic Communication Format) is used for message (de)serialization and allows to encode 
sender and receiver addresses within the message. 

Each server has its own interface defined in terms of supported methods, events and user-defined data types. 
The client can only communicate to a server by sending valid messages that conform to this interface. The interface itself
is defined in XML-file with HBGA extension. The closest analogies for this approach are DBus in Linux KDE and WebServices.

Similarly to the analogies, the corresponding language bindings for client and server implementations are generated 
on a basis of the provided interfaces: <InterfaceName>Proxy for client, <InterfaceName>Stub for server. Currently, 
only Java bindings are supported. The tool used for generation is GCFAPICodegen. 

It is possible to instantiate several servers. One server can serve for several clients.
