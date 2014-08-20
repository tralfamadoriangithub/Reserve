package com.epam.task6.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspWriter;

import com.epam.task6.entity.ClaimStatusStringValue;
import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectString;
import com.epam.task6.tableentity.ClaimTableEntity;

public class OperatorClaimsTableTag extends UserClaimsTableTag {

	private static final long serialVersionUID = 1L;
	private ResourceBundle bundle;

	@Override
	protected void printClaim( ClaimTableEntity claim, JspWriter out )
			throws IOException {
		bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_STRING,
				Locale.getDefault() );

		if ( claim.getClaimStatus().equals( ClaimStatusStringValue.EXCEPTED ) ) {
			super.printClaim( claim, out );
			out.write( "<td>" );
			out.write( "<form action='controller' method='post'>" );
			out.write( "<input type='hidden' name='command' value='create_assignation_command'/>" );
			out.write( "<input type='hidden' name='claim_id' value='" );
			out.write( String.valueOf( claim.getClaimId() ) );
			out.write( "'/><input type='hidden' name='problem_description' value='" );
			out.write( claim.getProblemDescription() );
			out.write( "'/><input type='hidden' name='claim_status' value='" );
			out.write( claim.getClaimStatus() );
			out.write( "'/><input type='hidden' name='address_id' value='" );
			out.write( String.valueOf( claim.getAddress().getAddressId() ) );
			out.write( "'/><input type='hidden' name='street' value='" );
			out.write( claim.getAddress().getStreet() );
			out.write( "'/><input type='hidden' name='house' value='" );
			out.write( String.valueOf( claim.getAddress().getHouseNumber() ) );
			out.write( "'/><input type='hidden' name='block' value='" );
			out.write( String.valueOf( claim.getAddress().getBlockNumber() ) );
			out.write( "'/><input type='hidden' name='flat' value='" );
			out.write( String.valueOf( claim.getAddress().getFlatNumber() ) );
			out.write( "'/><input type='hidden' name='phone' value='" );
			out.write( claim.getAddress().getPhone() );
			out.write( "'/><input type='hidden' name='user_id' value='" );
			out.write( String.valueOf( claim.getAddress().getUserId() ) );
			out.write( "'/><input type='submit' value='" );
			out.write( bundle.getString( ProjectString.ASSIGNATION ) );
			out.write( "'/></form></td>" );
		}
	}
}
