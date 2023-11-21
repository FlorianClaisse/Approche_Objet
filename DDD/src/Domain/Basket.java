package Domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Basket {
    private final Map<Reference, CommandLine> commandLines = new HashMap<>();
    private boolean validated = false;

    public int getAmount() {
        int total = 0;
        for(CommandLine command: commandLines.values()) {
            total += command.getAmount();
        }
        return total;
    }

    public void addReference(Reference reference, int quantity) {
        if (validated) return;
        CommandLine newCommandLine = new CommandLine(reference, quantity);
        commandLines.put(reference, newCommandLine);
    }

    public void removeReference(Reference reference) {
        if (validated) return;
        CommandLine commandToRemove = new CommandLine(reference, 0);
        commandLines.remove(reference);
    }

    public void validate() { validated = true; }
}
