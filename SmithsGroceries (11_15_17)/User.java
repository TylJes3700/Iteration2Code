public class User {
   private String name;
   private String password;
   private boolean isManager;
   private int pic;
   
   User(String name, String password, boolean isManager) {
      this.name = name;
      this.password = password;
      this.isManager = isManager;
      this.pic = 44;
   }
   
   User(String name, String password, boolean isManager, int pic) {
      this.name = name;
      this.password = password;
      this.isManager = isManager;
      this.pic = pic;
   }

   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
   
   public boolean isManager() {
      return isManager;
   }
   
   public void setManager(boolean isManager) {
      this.isManager = isManager;
   }
   
   public int getPic() {
      return pic;
   }
   
   public void setPic(int pic) {
      this.pic = pic;
   }
}