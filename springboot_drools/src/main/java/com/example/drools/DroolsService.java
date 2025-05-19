import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

    private KieServices kieServices = KieServices.Factory.get();
    private KieContainer kieContainer;
    private KieSession kieSession;

    public DroolsService() {
        initKieSession();
    }

    private void initKieSession() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rules/agendagroup.drl", getDroolsRule());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        kieSession = kieContainer.newKieSession();
    }

    public void fireRules() {
        kieSession.fireAllRules();
    }

    public void updateRules(String newRule) {
        // 销毁旧的KieSession
        if (kieSession != null) {
            kieSession.dispose();
        }

        // 创建新的KieSession
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rules/agendagroup.drl", newRule);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();
        kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        kieSession = kieContainer.newKieSession();
    }

    private String getDroolsRule() {
        return "package testagendagroup\n" +
               "rule \"rule_agendagroup_1\"\n" +
               "    agenda-group \"myagendagroup_1\"\n" +
               "    when\n" +
               "    then\n" +
               "        System.out.println(\"规则rule_agendagroup_1触发\");\n" +
               "end\n" +
               "rule \"rule_agendagroup_2\"\n" +
               "    agenda-group \"myagendagroup_1\"\n" +
               "    when\n" +
               "    then\n" +
               "        System.out.println(\"规则rule_agendagroup_2触发\");\n" +
               "end\n" +
               "rule \"rule_agendagroup_3\"\n" +
               "    agenda-group \"myagendagroup_2\"\n" +
               "    when\n" +
               "    then\n" +
               "        System.out.println(\"规则rule_agendagroup_3触发\");\n" +
               "end\n" +
               "rule \"rule_agendagroup_4\"\n" +
               "    agenda-group \"myagendagroup_2\"\n" +
               "    when\n" +
               "    then\n" +
               "        System.out.println(\"规则rule_agendagroup_4触发\");\n" +
               "end";
    }
}