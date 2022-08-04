public class StudentGrade {

    String studentID, programDesc, lastName, firstName, middleInitial,suffix, yearDesc, semDesc ,sectionDesc, listerDesc;
    Double gwa;
        StudentGrade (String studentID, String programDesc, String lastName, String firstName, String middleInitial, String suffix, String yearDesc, String semDesc,String sectionDesc, Double gwa, String listerDesc) {
            this.studentID = studentID;
            this.programDesc = programDesc;
            this.lastName = lastName;
            this.firstName = firstName;
            this.middleInitial = middleInitial;
            this.suffix = suffix;
            this.yearDesc =yearDesc;
            this.semDesc = semDesc;
            this.sectionDesc=sectionDesc;
            this.gwa = gwa;
            this.listerDesc = listerDesc;
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

        public String getsemDesc() {
            return this.semDesc;
        }

        public String getsectionDesc() {
            return this.sectionDesc;
        }

        public Double getgwa() {
            return this.gwa;
        }

        public String getlisterDesc() {
            return this.listerDesc;
        }

       @Override
       public String toString() {
            return (this.getstudentID()+this.getprogramDesc()+this.getlastName()+this.getfirstName()+this.getmiddleInitial()+this.getsuffix()+this.getyearDesc()+this.getsemDesc()+this.getsectionDesc()+this.getgwa()+this.getlisterDesc());
       }

}
