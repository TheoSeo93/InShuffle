import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class Card {
    private Suite suite;
    private Name name;
}
