### Here is the sample application with which you can reproduce the NPE:

The context is :<br/> 
Modules A and B are customers that have developped modules with the help of our own framework "fw".<br/>
"fw" is an intermediary layer on Restlet that provides the implementations of retrieving a single entity and of adding an entity to a collection.<br/><br/>
We meet a problem only when doing a POST, you have the exception stacktrace below.

But before, here are the possible manipulations:<br/>

####EntityServerResource<br/>
><pre>EntityServerResource<R extends Rep> extends ServerResource implements HasRetrieve<R></pre>
GET http://localhost:8183/entitiesA/10 -> OK<br/>
GET http://localhost:8183/entitiesB/11 -> OK<br/>


####EntitiesServerResource: the expected implementation:<br/>
><pre>EntitiesServerResource<R extends Rep> extends ServerResource implements HasAddItem<R></pre> 

>POST http://localhost:8183/entitiesA/
><pre>
<\?xml version="1.0" encoding="UTF-8" ?>
<\ARep>
  <\code>10<\/code>
<\/ARep>
</pre>
<b>-> KO, we have the NPE described below.</b>

>POST http://localhost:8183/entitiesB/
><pre>
<\?xml version="1.0" encoding="UTF-8" ?>
<\BRep>
  <\code>10</code>
<\/BRep>
</pre>
<b>-> KO</b>

####EntitiesServerResource: If we begin to remove the interfaces..<br/>
><pre>EntitiesServerResource<R extends Rep> extends ServerResource 
// implements HasAddItem<R></pre>

>POST http://localhost:8183/entitiesA/ -> <b>OK</b><br/>
>POST http://localhost:8183/entitiesB/ -> <b>KO</b>

---
####Here is the exception's stacktrace:<br/>
Mar 21, 2012 7:07:20 PM org.restlet.resource.ServerResource doCatch
WARNING: Exception or error caught in server resource
<b>java.lang.NullPointerException</b>
>        at java.lang.Class.isAssignableFrom(Native Method)
>        at org.restlet.engine.converter.DefaultConverter.getVariants(DefaultConverter.java:104)
>        at org.restlet.engine.converter.ConverterUtils.getVariants(ConverterUtils.java:93)
>        at org.restlet.service.ConverterService.getVariants(ConverterService.java:116)
>        at org.restlet.engine.resource.AnnotationInfo.getRequestVariants(AnnotationInfo.java:401)
>        at org.restlet.engine.resource.AnnotationInfo.isCompatibleRequestEntity(AnnotationInfo.java:603)
>        at org.restlet.engine.resource.AnnotationInfo.isCompatible(AnnotationInfo.java:576)
>        at org.restlet.resource.ServerResource.getVariants(ServerResource.java:851)
>        at org.restlet.resource.ServerResource.getVariants(ServerResource.java:826)
>        at org.restlet.resource.ServerResource.doNegotiatedHandle(ServerResource.java:627)
>        at org.restlet.resource.ServerResource.doConditionalHandle(ServerResource.java:336)
>        at org.restlet.resource.ServerResource.handle(ServerResource.java:899)
>        at org.restlet.resource.Finder.handle(Finder.java:243)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Router.doHandle(Router.java:428)
>        at org.restlet.routing.Router.handle(Router.java:645)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.engine.application.StatusFilter.doHandle(StatusFilter.java:151)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.engine.CompositeHelper.handle(CompositeHelper.java:208)
>        at org.restlet.engine.application.ApplicationHelper.handle(ApplicationHelper.java:75)
>        at org.restlet.Application.handle(Application.java:377)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Router.doHandle(Router.java:428)
>        at org.restlet.routing.Router.handle(Router.java:645)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Router.doHandle(Router.java:428)
>        at org.restlet.routing.Router.handle(Router.java:645)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.engine.application.StatusFilter.doHandle(StatusFilter.java:151)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.routing.Filter.doHandle(Filter.java:156)
>        at org.restlet.routing.Filter.handle(Filter.java:203)
>        at org.restlet.engine.CompositeHelper.handle(CompositeHelper.java:208)
>        at org.restlet.Component.handle(Component.java:389)
>        at org.restlet.Server.handle(Server.java:513)
>        at org.restlet.engine.connector.ServerConnectionHelper.handle(ServerConnectionHelper.java:250)
>        at org.restlet.engine.connector.ServerConnectionHelper.doHandleInbound(ServerConnectionHelper.java:183)
>        at org.restlet.engine.connector.BaseHelper$2.run(BaseHelper.java:585)
>        at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
>        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
>        at java.lang.Thread.run(Thread.java:662)
Mar 21, 2012 7:07:20 PM org.restlet.engine.log.LogFilter afterHandle<br/>INFO: 2012-03-21	19:07:20	127.0.0.1	-	-	8183	POST	/entitiesB/	-	500	486	72	4	http://localhost:8183	Java/1.6.0_26	-

