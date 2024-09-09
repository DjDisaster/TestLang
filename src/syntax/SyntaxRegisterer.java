package syntax;

import java.util.ArrayList;
import java.util.List;

public class SyntaxRegisterer {
    private List<Syntax> syntax = newSyntax();
    public List<Syntax> newSyntax() {
        ArrayList<Syntax> syntax = new ArrayList<>();
        syntax.add(new Syntax("broadcast %string%", "Bukkit.broadcastMessage(\"%expr-1%\")"));
        return syntax;
    }

    public List<Syntax> getEffectsSyntax() {
        return syntax;
    }

}
