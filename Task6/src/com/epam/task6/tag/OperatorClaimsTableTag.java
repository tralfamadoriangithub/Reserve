package com.epam.task6.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.epam.task6.tableentity.ClaimTableEntity;

public class OperatorClaimsTableTag extends ClaimsTableTag{

	private static final long serialVersionUID = 1L;

	@Override
	protected void printClaim( ClaimTableEntity claim, JspWriter out )
			throws IOException {
		
		super.printClaim( claim, out );
		out.write( "<td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='delete_claim_command'/>"
				+ "<input type='hidden' name='claim_id' value='" + claim.getClaimId()
				+ "'/>" + "<input type='submit' value='Delete'/>" + "</form>" );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='create_assignation_command'/>"
				+ "<input type='hidden' name='claim_id' value='" + claim.getClaimId() + "'/>"
				+ "<input type='hidden' name='problem_description' value='" + claim.getProblemDescription() + "'/>"
				+ "<input type='hidden' name='claim_status' value='" + claim.getClaimStatus() + "'/>"
				+ "<input type='hidden' name='address_id' value='" + claim.getAddress().getAddressId() + "'/>"
				+ "<input type='hidden' name='street' value='" + claim.getAddress().getStreet() + "'/>"
				+ "<input type='hidden' name='house' value='" + claim.getAddress().getHouseNumber() + "'/>"
				+ "<input type='hidden' name='block' value='" + claim.getAddress().getBlockNumber() + "'/>"
				+ "<input type='hidden' name='flat' value='" + claim.getAddress().getFlatNumber() + "'/>"
				+ "<input type='hidden' name='phone' value='" + claim.getAddress().getPhone() + "'/>"
				+ "<input type='hidden' name='user_id' value='" + claim.getAddress().getUserId() + "'/>"
				+ "<input type='submit' value='Assignation'/>" + "</form>" );
		out.write( "</td>" );
		
	}
}
