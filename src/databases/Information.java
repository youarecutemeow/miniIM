package databases;

import java.io.InputStream;

public class Information {
    private String id;
    private String alias;
    private byte[] photo;
    private String profile;
    private int sex;
    private int age;

    public Information(String id, String alias, byte[] photo, String profile, int sex, int age) {
        this.id = id;
        this.alias = alias;
        this.sex = sex;
        this.photo = photo;
        this.age = age;
        this.profile = profile;
    }

    public String getAlias() {
        return alias;
    }

    public String getId() {
        return id;
    }

    public String getProfile() {
        return profile;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id:").append(id).append("\t").
                append("alias:").append(alias).append("\t").
                append("age:").append(age).append("\t").
                append("sex:").append(sex).append("\t");
        return sb.toString();
    }
}
