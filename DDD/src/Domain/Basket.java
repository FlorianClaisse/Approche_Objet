package Domain;

import java.util.HashSet;
import java.util.Set;

public final class Basket {
    private static int commandCounter = 0;
    private final Set<CommandLine> commandLines = new HashSet<>();
    private boolean validated = false;

    public int getAmount() {
        int total = 0;
        for(CommandLine command: commandLines) {
            total += command.getAmount();
        }
        return total;
    }

    public boolean addReference(Reference reference, int quantity) {
        if (validated) return false;
        CommandLine newCommandLine = new CommandLine(String.valueOf(commandCounter), reference, quantity);
        boolean res = commandLines.add(newCommandLine);
        commandCounter++;
        return res;
    }

    public void removeReference(Reference reference) {
        if (validated) return;
        CommandLine commandToRemove = new CommandLine(String.valueOf(commandCounter), reference, 0);
        commandLines.removeIf(c -> c.getProductRef().getRef().equals(reference.getRef()));
    }

    public void validate() { validated = true; }
}
