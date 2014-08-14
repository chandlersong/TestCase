package com.droolstuday.example.utils;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class DroolUtils {

	public static StatelessKnowledgeSession createKSession(String drlFile) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(drlFile), ResourceType.DRL);
		KnowledgeBase kbase = kbuilder.newKnowledgeBase();
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		return ksession;
	}

}
