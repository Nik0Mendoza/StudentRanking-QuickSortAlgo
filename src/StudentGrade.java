public class StudentGrade {

    String studentID, programDesc, lastName, firstName, middleInitial,suffix, yearDesc, sectionDesc;
    float gwa;
        StudentGrade (String studentID, String programDesc, String lastName, String firstName, String middleInitial, String suffix, String yearDesc, String sectionDesc, float gwa) {
            this.studentID = studentID;
            this.programDesc = programDesc;
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleInitial = middleInitial;
            this.suffix = suffix;
            this.yearDesc =yearDesc;
            this.sectionDesc=sectionDesc;
            this.gwa =gwa;
        }

        public String getstudentID() {
            return this.studentID;
        }

        public String getprogramDesc() {
            return this.programDesc;  
        }
        
        public String getlastName() {
            return this.lastName;
        }

        public String getfirstName() {
            return this.firstName;
        }

        public String getmiddleInitial() {
            return this.middleInitial;
        }

        public String getsuffix() {
            return this.suffix;
        }

        public String getyearDesc() {
            return this.yearDesc;
        }

        public String getsectionDesc() {
            return this.sectionDesc;
        }

        public float getgwa() {
            return this.gwa;
        }

       @Override
       public String toString() {
            return (this.getstudentID()+this.getprogramDesc()+this.getlastName()+this.getfirstName()+this.getmiddleInitial()+this.getsuffix()+this.getyearDesc()+this.getsectionDesc()+this.getgwa());
       }

}
