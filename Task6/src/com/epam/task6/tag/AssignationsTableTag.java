package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Assignation;
import com.epam.task6.entity.Claim;

public class AssignationsTableTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	private List<Assignation> assignations;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table border='1'>" );
			for(Assignation assignation: assignations){
				System.out.println(assignation);
				printAssignationRow( assignation, out );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private void printAssignationRow( Assignation assignation, JspWriter out ) throws IOException{
		out.write( "<tr><td>" );
		out.write( String.valueOf( assignation.getAssignationId() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( assignation.getBeginWork() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( assignation.getEndWork() ) );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='edit_claim_command'/>"
				+ "<input type='hidden' name='address' value='" + assignation
				+ "'/>" + "<input type='submit' value='Change Status'/>" + "</form>" );
		out.write( "</td></tr>" );
	}

	public List<Assignation> getAssignations() {
		return assignations;
	}
	
	public Assignation getAssignations( int index ){
		return assignations.get( index );
	}

	public void setAssignations( List<Assignation> assignations ) {
		this.assignations = assignations;
	}
	
	public void setAssignations( Assignation assignation ){
		assignations.add( assignation );
	}
}
