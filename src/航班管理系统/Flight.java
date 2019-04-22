package 航班管理系统;

public class Flight implements FlightInterface {
	 private String flightName;
	    private int row;
	    private int rowLength;
	    private int[] fail = {-1};
	    private Passenger[] passengerList;
		/**
		 * 航班类的构造方法
		 * @param FlightName 航班名
		 * @param rows 多少排座位
		 * @param rowLength 每排座位数
		 * @throws Exception 抛出异常类
		 */
		public Flight(String FlightName,int rows,int rowLength)throws Exception{
			if(FlightName==null||FlightName.trim().length()==0||rows<=0||rowLength<=0)
				throw new Exception("Error");
			else {
				this.flightName=FlightName;
				this.row=rows;
				this.rowLength=rowLength;
				this.passengerList=new Passenger[row*rowLength];
				for(int i=0;i<row*rowLength;i++)
					passengerList[i]=null;
			}
		}

	@Override
	public int[] reserve(String[] names) {
		// TODO Auto-generated method stub
		if(names.length>rowLength)
			return fail;
		int i=0,j=0,k=0;
		//true--能安排，false--不能安排
		boolean flag=false;
		//在同一排找相邻且没有被预定的座位，座位个数是 names.length
		labelA:for(i=0;i<=row-1;i++) {
			for(j=0;k<=j+names.length-1;k++) {
				//在本行从j到j+names.length-1 找到这样的空位
				for(k=j;k<=j+names.length-1;k++) {
					if(passengerList[i*rowLength+k]!=null)
						break;
				}
				if(k>j+names.length-1) {
					flag=true;
					break labelA;
				}
			}
		}
		if(!flag)
			return fail;
		//从第i行第j行开始分配座位
		int[]bn=new int[names.length];
		for(k=j;k<=j+names.length-1;k++) {
			bn[k-j]=i*rowLength+k+1;
			passengerList[i*rowLength+k]=new Passenger(names[k-j],i*rowLength+k+1,i,k);
		}
		return bn;
	}

	public Passenger[] getPassengerList() {
		// TODO Auto-generated method stub
		return passengerList;
	}

	@Override
	public boolean canncel(int bookingNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
