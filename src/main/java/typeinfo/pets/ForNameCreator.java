//: typeinfo/pets/ForNameCreator.java
package typeinfo.pets;

import java.util.*;

public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();
    // Types that you want to be randomly created:
    private static String[] typeNames = {
            "typeinfo.pets.Mutt",
            "typeinfo.pets.Pug",
            "typeinfo.pets.EgyptianMau",
            "typeinfo.pets.Manx",
            "typeinfo.pets.Cymric",
            "typeinfo.pets.Rat",
            "typeinfo.pets.Mouse",
            "typeinfo.pets.Hamster"
    };

    @SuppressWarnings("unchecked")
    private static void loader() {
        try {
            for (String name : typeNames)
                types.add((Class<? extends Pet>) Class.forName(name));     //通过forName获取Pet类型的Class对象（对比LiteralPetCreator）
        } catch (ClassNotFoundException e) {                               //编译时无法校验类名的正确性，因此运行时会抛出这个异常很合理。
            throw new RuntimeException(e);
        }
    }

    static {
        loader();
    }

    public List<Class<? extends Pet>> types() {
        return types;
    }
} ///:~
