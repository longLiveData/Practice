public class LookupPhonebook
{
    public static void main(String[] args) {
        Phonebook pb = new Phonebook("Sam Johnson");
        pb.addContact(new Contact("Kelly Wong", "(02) 12345678"));
        pb.addContact(new Contact("Richard Jackson", "(02) 87654321"));
        pb.show();
        
        String name = Input.askString("Enter a contact name:");
        Contact c = pb.findContactByName(name);
        if (c == null) {
            System.out.println(name + " not found");
        } else {
            System.out.println("Phone number is " + c.getPhone());
        }
    }
}
