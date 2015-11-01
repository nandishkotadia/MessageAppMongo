# MessageAppMongo
Messaging App using Java, Spring, Websockets and MongoDB 
(This project is just created as a miniproject to learn websockets)

For running the app locally....

1.) Edit conf/application.properties and comment out the below line as shown:
    #spring.data.mongodb.uri: mongodb://admin:admin@ds045704.mongolab.com:45704/messageapp
    and uncomment the following line as shown below:
    spring.data.mongodb.uri: mongodb://localhost/messageapp
2.) Start/Run the App.java file (MessageApp is now started)
3.) Visit localhost:(port)/index.html
    NOTE: replace (port) with your app port which appears when you start your app(Might be 8800 or 8080 whichever you have set). 4.) Session is created using HTML localstorage i.e. on client side. Can move session to serverside 


