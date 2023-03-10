package coffee.amo.apothecary.compat;

public class CompatHolder {
    public String modid;
    public String className;
    public String methodName;

    public String getDamageMethodName;
    // list of parameters
    public String[] parameters;

    public CompatHolder(String modid, String className, String methodName, String[] parameters, String getDamageMethodName) {
        this.modid = modid;
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
        this.getDamageMethodName = getDamageMethodName;
    }
}
