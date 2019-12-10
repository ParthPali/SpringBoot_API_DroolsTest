package com.javainuse.main;

import com.javainuse.model.Product;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.drools.core.rule.Package;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
public class SpringBootUtility {
    public Package sample;

    public SpringBootUtility() {
        this.sample = createSample("com/rule/Rules.drl");
    }

    private Package createSample(String drl) {
        PackageBuilder packageBuilder = new PackageBuilder();
        InputStream inputStream = null;
        inputStream = SpringBootApiDemo.class.getClassLoader().getResourceAsStream(drl);
        Reader reader = new InputStreamReader(inputStream);
        try {
            packageBuilder.addPackageFromDrl(reader);
        } catch (DroolsParserException | IOException e) {
            e.printStackTrace();
        }
        org.drools.core.rule.Package rulesPackage = packageBuilder.getPackage();
        return rulesPackage;
    }
    public Product execute(Product product) {
        RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        ruleBase.addPackage(sample);
        WorkingMemory workingMemory = ruleBase.newStatefulSession();

        workingMemory.insert(product);
        workingMemory.fireAllRules();

        return product;
    }
}
