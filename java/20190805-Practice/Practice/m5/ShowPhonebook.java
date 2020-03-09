public class ShowPhonebook
{
    public static void main(String[] args) {
        Phonebook pb = new Phonebook("Sam Johnson");
        pb.addContact(new Contact("Kelly Wong", "(02) 12345678"));
        pb.addContact(new Contact("Richard Jackson", "(02) 87654321"));
        pb.show();
    }
}
