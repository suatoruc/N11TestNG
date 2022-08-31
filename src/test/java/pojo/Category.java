package pojo;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    private int id;
    private String name;

    public Category(String name) {
    }
}
