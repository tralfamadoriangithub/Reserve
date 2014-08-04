package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Address;

public class AddressesTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<Address> addresses;

	public void setAddresses( List<Address> addresses ) {
		this.addresses = addresses;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table border='1'>" );
			out.write( "<th>Street</th><th>House</th><th>Block</th><th>Flat</th>" );
			for ( Address address : addresses ) {
				System.out.println( address );
				printAddress( address, out );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {

			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	private void printAddress( Address address, JspWriter out )
			throws IOException {
		String addressId = String.valueOf( address.getAddressId() );
		String street = address.getStreet();
		String house = String.valueOf( address.getHouseNumber() );
		String block = String.valueOf( address.getBlockNumber() );
		String flat = String.valueOf( address.getFlatNumber() );
		String userId = String.valueOf( address.getUserId() );
		out.write( "<tr><td>" );
		out.write( street );
		out.write( "</td><td>" );
		out.write( house );
		out.write( "</td><td>" );
		out.write( block );
		out.write( "</td><td>" );
		out.write( flat );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='create_claim_command'/>"
//				+ "<input type='hidden' name='address' value='" + address + "'/>" 
				+ "<input type='hidden' name='address_id' value='" + addressId + "'/>" 
				+ "<input type='hidden' name='street' value='" + street + "'/>"
				+ "<input type='hidden' name='house' value='" + house + "'/>"
				+ "<input type='hidden' name='block' value='" + block + "'/>"
				+ "<input type='hidden' name='flat' value='" + flat + "'/>"
				+ "<input type='hidden' name='user_id' value='" + userId + "'/>"
				+ "<input type='submit' value='Claim'/>" + "</form>" );
		out.write( "</td></tr>" );
	}

}
