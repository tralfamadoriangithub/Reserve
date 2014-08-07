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
				+ "<input type='hidden' name='claim' value='" + claim
				+ "'/>" + "<input type='submit' value='Delete Claim'/>" + "</form>" );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='create_assignation_command'/>"
				+ "<input type='hidden' name='claim' value='" + claim
				+ "'/>" + "<input type='submit' value='Create Assignation'/>" + "</form>" );
		out.write( "</td>" );
		
	}
}
