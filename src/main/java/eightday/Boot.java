package eightday;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Boot {

  private static final String SPACE = " ";
  public static final String PATH = ".\\src\\main\\resources\\sequence.txt";

  static int currentIndex = 0;
  static int accumulator = 0;

  public static void main(String[] args) throws IOException {
    ExecutionEntry[] program = readProgramSequence();
    do {
      ExecutionEntry command = program[currentIndex];
      execute(command);
    } while (currentIndex < program.length);
  }

  private static ExecutionEntry[] readProgramSequence() throws IOException {
    List<String> commandsSequence = Files.readAllLines(Paths.get(PATH));
    ExecutionEntry[] program = new ExecutionEntry[commandsSequence.size()];
    for (int i = 0; i < commandsSequence.size(); i++) {
      String[] command = commandsSequence.get(i).split(SPACE);
      program[i] = new ExecutionEntry(Operations.getFromKey(command[0]), Integer.valueOf(command[1]));
    }
    return program;
  }

  private static void execute(ExecutionEntry command) {
    if (command.isExecuted()) {
      log.error(
          "Entering the same command more than once! The current accumulator value is: "
              + accumulator);
      System.exit(1); // prevent infinite loop
    }
    command.setExecuted(true);
    switch (command.getOperation()) {
      case ACCUMULATOR:
        accumulator += command.getValue();
        currentIndex++;
        break;
      case NO_OPERATION:
        currentIndex++;
        break;
      case JUMP:
        currentIndex += command.getValue();
        break;
      default:
        throw new NoSuchElementException("Operation: " + command.getOperation() + " is unknown");
    }
  }
}
