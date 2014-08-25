package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Address;
import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectString;
/**
 * Класс, реализующий пользовательский тег вывода таблицы адресов.
 * @author dmitry
 *
 */
public class AddressesTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<Address> addresses;
	private ResourceBundle bundle;

	@Override
	public int doStartTag() throws JspException {
		bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_STRING,
				Locale.getDefault() );
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table>" );

			StringBuilder tableHeader = new StringBuilder();
			tableHeader.append( "<th>" )
					.append( bundle.getString( ProjectString.STREET ) )
					.append( "</th><th>" )
					.append( bundle.getString( ProjectString.HOUSE ) )
					.append( "</th><th>" )
					.append( bundle.getString( ProjectString.BLOCK ) )
					.append( "</th><th>" )
					.append( bundle.getString( ProjectString.FLAT ) )
					.append( "</th><th>" )
					.append( bundle.getString( ProjectString.PHONE ) )
					.append( "</th>" );

			out.write( tableHeader.toString() );
			if ( addresses != null ) {
				for ( Address address : addresses ) {
					printAddressRow( address, out );
				}
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			throw new JspException();
		}

		return SKIP_BODY;
	}

	private void printAddressRow( Address address, JspWriter out )
			throws IOException {
		String addressId = String.valueOf( address.getAddressId() );
		String street = address.getStreet();
		String house = String.valueOf( address.getHouseNumber() );
		String block = String.valueOf( address.getBlockNumber() );
		String flat = String.valueOf( address.getFlatNumber() );
		String userId = String.valueOf( address.getUserId() );
		String phone = address.getPhone();
		out.write( "<tr><td>" );
		out.write( street );
		out.write( "</td><td>" );
		out.write( house );
		out.write( "</td><td>" );
		out.write( block );
		out.write( "</td><td>" );
		out.write( flat );
		out.write( "</td><td>" );
		out.write( phone );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='create_claim_command'/>"
				+ "<input type='hidden' name='address_id' value='" + addressId
				+ "'/>" + "<input type='hidden' name='street' value='" + street
				+ "'/>" + "<input type='hidden' name='house' value='" + house
				+ "'/>" + "<input type='hidden' name='block' value='" + block
				+ "'/>" + "<input type='hidden' name='flat' value='" + flat
				+ "'/>" + "<input type='hidden' name='user_id' value='"
				+ userId + "'/>" + "<input type='submit' value='"
				+ bundle.getString( ProjectString.CLAIM ) + "'/>" + "</form>" );
		out.write( "</td></tr>" );
	}

	public void setAddresses( List<Address> addresses ) {
		this.addresses = addresses;
	}

	public void setAddresses( Address address ) {
		addresses.add( address );
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public Address getAddresses( int index ) {
		return addresses.get( index );
	}
}
