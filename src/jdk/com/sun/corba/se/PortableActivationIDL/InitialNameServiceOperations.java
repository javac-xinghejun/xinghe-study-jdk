package com.sun.corba.se.PortableActivationIDL;


/**
* com/sun/corba/se/PortableActivationIDL/InitialNameServiceOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /Users/java_re/workspace/8-2-build-macosx-x86_64/jdk8u221/13320/corba/src/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Thursday, July 4, 2019 4:38:18 AM PDT
*/


/** Interface used to support binding references in the bootstrap name
    * service.
    */
public interface InitialNameServiceOperations 
{

  /** bind initial name
  	*/
  void bind (String name, org.omg.CORBA.Object obj, boolean isPersistant) throws com.sun.corba.se.PortableActivationIDL.InitialNameServicePackage.NameAlreadyBound;
} // interface InitialNameServiceOperations
