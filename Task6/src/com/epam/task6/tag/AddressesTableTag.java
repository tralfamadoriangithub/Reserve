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
		out.write( "<tr><td>" );
		out.write( address.getStreet() );
		out.write( "</td><td>" );
		out.write( String.valueOf( address.getHouseNumber() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( address.getBlockNumber() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( address.getFlatNumber() ) );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='create_claim_command'/>"
				+ "<input type='hidden' name='address' value='" + address
				+ "'/>" + "<input type='submit' value='Claim'/>" + "</form>" );
		out.write( "</td></tr>" );
	}

}
