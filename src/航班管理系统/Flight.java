package �������ϵͳ;

public class Flight implements FlightInterface {
	 private String flightName;
	    private int row;
	    private int rowLength;
	    private int[] fail = {-1};
	    private Passenger[] passengerList;
		/**
		 * ������Ĺ��췽��
		 * @param FlightName ������
		 * @param rows ��������λ
		 * @param rowLength ÿ����λ��
		 * @throws Exception �׳��쳣��
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
		//true--�ܰ��ţ�false--���ܰ���
		boolean flag=false;
		//��ͬһ����������û�б�Ԥ������λ����λ������ names.length
		labelA:for(i=0;i<=row-1;i++) {
			for(j=0;k<=j+names.length-1;k++) {
				//�ڱ��д�j��j+names.length-1 �ҵ������Ŀ�λ
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
		//�ӵ�i�е�j�п�ʼ������λ
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
