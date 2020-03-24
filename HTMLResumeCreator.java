import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HTMLResumeCreator {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("You must use 1 parameters, like follow:");
			System.out.println("java HTMLResumeCreator file");
			System.exit(1);
		}
		
		try {
//			BufferedReader br = new BufferedReader(new FileReader("testcv"));	//DEBUG
//			BufferedWriter bw = new BufferedWriter(new FileWriter("testcv.html"));	//DEBUG
			BufferedReader br = new BufferedReader(new FileReader(args[0]));	//NON DEBUG
			BufferedWriter bw = new BufferedWriter(new FileWriter(args[0] + ".html"));	//NON DEBUG
			String line = br.readLine();
			HashMap<String, String> contact = new HashMap<String, String>();
			HashMap<String, String> skills = new HashMap<String, String>();
			ArrayList<String> technical = new ArrayList<>();
//			ArrayList<HashMap<String, String>> experience = new ArrayList<>();
//			HashMap<String, String> jobexperience = new HashMap<String, String>();
			String experience = "";
//			ArrayList<HashMap<String, String>> education = new ArrayList<>();
//			HashMap<String, String> career = new HashMap<String, String>();
			String education = "";
			
			while (line != null) {
				
				switch (line) {
				case ">>>name":
					contact.put("name", br.readLine());
					line = br.readLine();
					break;
				case ">>>header":
					contact.put("header", br.readLine());
					line = br.readLine();
					break;
				case ">>>email":
					contact.put("email", br.readLine());
					line = br.readLine();
					break;
				case ">>>phone":
					contact.put("phone", br.readLine());
					line = br.readLine();
					break;
				case ">>>profile":
					contact.put("profile", br.readLine());
					line = br.readLine();
					break;
				case ">>>skills":
					line = br.readLine();
					while (line.startsWith("+")) {
						skills.put(line.substring(1), br.readLine());
						line = br.readLine();
					}
					break;
				case ">>>technical":
					line = br.readLine();
					while (! line.startsWith(">>>")) {
						technical.add(line);
						line = br.readLine();
					}
					break;
				case ">>>experience":
					experience = "					<div class=\"yui-gf\">\n" + 
							"	\n" + 
							"						<div class=\"yui-u first\">\n" + 
							"							<h2>Experience</h2>\n" + 
							"						</div><!--// .yui-u -->\n" + 
							"\n" + 
							"						<div class=\"yui-u\">";
					line = br.readLine();
					while (line.equals("+company")) {
						String company = "";
						String jobtitle = "";
						String date = "";
						String jobdesc = "";
						company = br.readLine();
						if (br.readLine().equals("+jobtitle")) {
							jobtitle = br.readLine();
						} 
						if (br.readLine().equals("+date")) {
							date = br.readLine();
						}
						if (br.readLine().equals("+jobdesc")) {
							jobdesc = br.readLine();
						}
						
						
						line = br.readLine();
						if (! line.startsWith("+company")) {
							experience += "\n							<div class=\"job last\">\n";
						} else {
							experience += "\n							<div class=\"job\">\n";
						}
						experience += "								<h2>" + company + "</h2>\n" + 
								"								<h3>" + jobtitle + "</h3>\n" + 
								"								<h4>" + date + "</h4>\n" + 
								"								<p>" + jobdesc + "</p>\n" + 
								"							</div>";
					}
					break;
					//NOT WORKING
//				case ">>>experience":
//					line = br.readLine();
//					while (line.startsWith("+company")) {
//						jobexperience.clear();
//						jobexperience.put(line.substring(1), br.readLine());
//						line = br.readLine();
//						while (! line.startsWith("+company") & line.startsWith("+")) {
//							jobexperience.put(line.substring(1), br.readLine());
////							System.out.println("CONTENT:" + line);
//							line = br.readLine();
//						}
//						for (Map.Entry<String, String> job : jobexperience.entrySet()) {
//							System.out.println("KEY:" + job.getKey());
//							System.out.println("VALUE:" + job.getValue());
//						}
//						System.out.println("-------------------------------------------");
//						experience.add(jobexperience);
//					}
//					break;
					
					
				case ">>>education":
//					education = "\n					<div class=\"yui-gf\">\n" + 
//							"	\n" + 
//							"						<div class=\"yui-u first\">\n" + 
//							"							<h2>Experience</h2>\n" + 
//							"						</div><!--// .yui-u -->\n" + 
//							"\n" + 
//							"						<div class=\"yui-u\">";
					line = br.readLine();
					while (line.equals("+school")) {
						String school = "";
						String specialty = "";
						String marks = "";
						school = br.readLine();
						if (br.readLine().equals("+specialty")) {
							specialty = br.readLine();
						} 
						if (br.readLine().equals("+marks")) {
							marks = br.readLine();
						}
						
						
						line = br.readLine();
						if (! line.startsWith("+school")) {
							education += "\n					<div class=\"yui-gf last\">";
						} else {
							education += "\n					<div class=\"yui-gf\">";
						}
						education += "\n						<div class=\"yui-u first\">\n" + 
								"							<h2>Education</h2>\n" + 
								"						</div>\n" + 
								"						<div class=\"yui-u\">\n" + 
								"							<h2>" + school + "</h2>\n" + 
										"							<h3>" + specialty + " &mdash; <strong>" + marks + "</strong> </h3>\n" + 
												"						</div>\n" + 
												"					</div><!--// .yui-gf -->\n" + 
												"\n" + 
												"\n" + 
												"				</div><!--// .yui-b -->\n" + 
												"			</div><!--// yui-main -->\n" + 
												"		</div><!--// bd -->";
					}
					break;
					//NOT WORKING
//				case ">>>education":
//					line = br.readLine();
//					while (line.startsWith("+school")) {
//						career.put(line.substring(1), br.readLine());
//						line = br.readLine();
//						while (! line.startsWith("+school") & line.startsWith("+")) {
//							career.put(line.substring(1), br.readLine());
//							line = br.readLine();
//						}
//						education.add(career);
//					}
//					break;
					
					
				default:
					System.out.println("DEFAULT:" + line);
					break;
					
					
					
					
					
				}
				if (! line.startsWith(">>>")) {
					line = br.readLine();
				}
				
			}
			System.out.println("WRITING");	//DEBUG
			bw.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" + 
					"<html>\n" + 
					"<head>\n");
			bw.write("	<title>" + contact.get("name") + " | " + contact.get("header") + " | " + contact.get("email") + "</title>\n" + 
					"	<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />\n" + 
					"\n" + 
					"	<meta name=\"keywords\" content=\"\" />\n" + 
					"	<meta name=\"description\" content=\"\" />\n" + 
					"\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"http://yui.yahooapis.com/2.7.0/build/reset-fonts-grids/reset-fonts-grids.css\" media=\"all\" /> \n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"./testcv.css\" media=\"all\" />\n" + 
					"\n" + 
					"</head>\n");
			bw.write("<body>\n" + 
					"<div id=\"doc2\" class=\"yui-t7\">\n" + 
					"	<div id=\"inner\">\n" + 
					"		<div id=\"hd\">\n" + 
					"			<div class=\"yui-gc\">\n" + 
					"				<div class=\"yui-u first\">\n" + 
					"					<h1>" + contact.get("name") + "</h1>\n" + 
					"					<h2>" + contact.get("header") + "</h2>\n" + 
					"				</div>\n" + 
					"\n" + 
					"				<div class=\"yui-u\">\n" + 
					"					<div class=\"contact-info\">\n" + 
					"						<h3><a href=\"mailto:" + contact.get("email") + "\">" + contact.get("email") + "</a></h3>\n" + 
					"						<h3>" + contact.get("phone") + "</h3>\n" + 
					"					</div><!--// .contact-info -->\n" + 
					"				</div>\n" + 
					"			</div><!--// .yui-gc -->\n" + 
					"		</div><!--// hd -->");
			bw.write("		<div id=\"bd\">\n" + 
					"			<div id=\"yui-main\">\n" + 
					"				<div class=\"yui-b\">\n" + 
					"\n" + 
					"					<div class=\"yui-gf\">\n" + 
					"						<div class=\"yui-u first\">\n" + 
					"							<h2>Profile</h2>\n" + 
					"						</div>\n" + 
					"						<div class=\"yui-u\">\n" + 
					"							<p class=\"enlarge\">\n" + 
					"								" + contact.get("profile") + " \n" + 
					"							</p>\n" + 
					"						</div>\n" + 
					"					</div><!--// .yui-gf -->\n" + 
					"					<div class=\"yui-gf\">\n" + 
					"						<div class=\"yui-u first\">\n" + 
					"							<h2>Skills</h2>\n" + 
					"						</div>\n" + 
					"						<div class=\"yui-u\">");
//			#=====SKILLS=====#
			for (Map.Entry<String, String> skill: skills.entrySet()) {
				bw.write("								<div class=\"talent\">\n" + 
						"									<h2>" + skill.getKey() + "</h2>\n" + 
						"									<p>" + skill.getValue() + "</p>\n" + 
						"								</div>");
			}
			bw.write("						</div>\n" + 
					"					</div><!--// .yui-gf -->\n" + 
					"\n" + 
					"					<div class=\"yui-gf\">\n" + 
					"						<div class=\"yui-u first\">\n" + 
					"							<h2>Technical</h2>\n" + 
					"						</div>\n" + 
					"						<div class=\"yui-u\">");
//			#=====TECHNICAL=====#
			for (int i = 0; i < technical.size(); i++) {
				if (i % 3 == 0) {
					bw.write("							<ul class=\"talent\">\n");
				}
				if (i == (technical.size() - 1) | ((i + 1) % 3 == 0) & i != 0) {
					bw.write("								<li>" + technical.get(i) + "</li>\n");
					bw.write("							</ul>\n");
				} else {
					bw.write("								<li>" + technical.get(i) + "</li>\n");
				}
			}
			
			bw.write("						</div>\n" + 
					"					</div><!--// .yui-gf-->\n" + 
					"\n");
//			#=====EXPERIENCE=====#
//			for (int i = 0; i < experience.size(); i++) {
//				jobexperience = experience.get(i);
//				for (Map.Entry<String, String> job : jobexperience.entrySet()) {
//					System.out.println("KEY:" + job.getKey());
//					System.out.println("VALUE:" + job.getValue());
//				}
//				
//			}
			bw.write(experience + "\n						</div><!--// .yui-u -->\n" + 
					"					</div><!--// .yui-gf -->\n" + 
					"\n" + 
					"\n");
			
//			#=====EDUCATION=====#
//			for (int i = 0; i < education.size(); i++) {
//				for (Map.Entry<String, String> study : career.entrySet()) {
//					System.out.println("KEY:" + study.getKey());
//					System.out.println("VALUE:" + study.getValue());
//				}
//				
//			}
			bw.write(education);
			bw.write("\n" + 
					"		<div id=\"ft\">\n" + 
					"			<p>" + contact.get("name") + " &mdash; <a href=\"mailto:" + contact.get("email") + "\">" + contact.get("email") + "</a> &mdash; X" + contact.get("phone") + "</p>\n" + 
					"		</div><!--// footer -->\n" + 
					"\n" + 
					"	</div><!-- // inner -->\n" + 
					"\n" + 
					"\n" + 
					"</div><!--// doc -->\n" + 
					"\n" + 
					"\n" + 
					"</body>\n" + 
					"</html>");
			
			bw.close();
//			bw = new BufferedWriter(new FileWriter("testcv.css"));	//DEBUG
			bw = new BufferedWriter(new FileWriter(args[0] + ".css"));	//NON DEBUG
			
			bw.write("/*\n" + 
					"---------------------------------------------------------------------------------\n" + 
					"	STRIPPED DOWN RESUME TEMPLATE\n" + 
					"    html resume\n" + 
					"\n" + 
					"    v0.9: 5/28/09\n" + 
					"\n" + 
					"    design and code by: thingsthatarebrown.com \n" + 
					"                        (matt brown)\n" + 
					"---------------------------------------------------------------------------------\n" + 
					"*/\n" + 
					"\n" + 
					"\n" + 
					".msg { padding: 10px; background: #222; position: relative; }\n" + 
					".msg h1 { color: #fff;  }\n" + 
					".msg a { margin-left: 20px; background: #408814; color: white; padding: 4px 8px; text-decoration: none; }\n" + 
					".msg a:hover { background: #266400; }\n" + 
					"\n" + 
					"/* //-- yui-grids style overrides -- */\n" + 
					"body { font-family: Georgia; color: #444; }\n" + 
					"#inner { padding: 10px 80px; margin: 80px auto; background: #f5f5f5; border: solid #666; border-width: 8px 0 2px 0; }\n" + 
					".yui-gf { margin-bottom: 2em; padding-bottom: 2em; border-bottom: 1px solid #ccc; }\n" + 
					"\n" + 
					"/* //-- header, body, footer -- */\n" + 
					"#hd { margin: 2.5em 0 3em 0; padding-bottom: 1.5em; border-bottom: 1px solid #ccc }\n" + 
					"#hd h2 { text-transform: uppercase; letter-spacing: 2px; }\n" + 
					"#bd, #ft { margin-bottom: 2em; }\n" + 
					"\n" + 
					"/* //-- footer -- */\n" + 
					"#ft { padding: 1em 0 5em 0; font-size: 92%; border-top: 1px solid #ccc; text-align: center; }\n" + 
					"#ft p { margin-bottom: 0; text-align: center;   }\n" + 
					"\n" + 
					"/* //-- core typography and style -- */\n" + 
					"#hd h1 { font-size: 48px; text-transform: uppercase; letter-spacing: 3px; }\n" + 
					"h2 { font-size: 152% }\n" + 
					"h3, h4 { font-size: 122%; }\n" + 
					"h1, h2, h3, h4 { color: #333; }\n" + 
					"p { font-size: 100%; line-height: 18px; padding-right: 3em; }\n" + 
					"a { color: #990003 }\n" + 
					"a:hover { text-decoration: none; }\n" + 
					"strong { font-weight: bold; }\n" + 
					"li { line-height: 24px; border-bottom: 1px solid #ccc; }\n" + 
					"p.enlarge { font-size: 144%; padding-right: 6.5em; line-height: 24px; }\n" + 
					"p.enlarge span { color: #000 }\n" + 
					".contact-info { margin-top: 7px; }\n" + 
					".first h2 { font-style: italic; }\n" + 
					".last { border-bottom: 0 }\n" + 
					"\n" + 
					"\n" + 
					"/* //-- section styles -- */\n" + 
					"\n" + 
					"a#pdf { display: block; float: left; background: #666; color: white; padding: 6px 50px 6px 12px; margin-bottom: 6px; text-decoration: none;  }\n" + 
					"a#pdf:hover { background: #222; }\n" + 
					"\n" + 
					".job { position: relative; margin-bottom: 1em; padding-bottom: 1em; border-bottom: 1px solid #ccc; }\n" + 
					".job h4 { position: absolute; top: 0.35em; right: 0 }\n" + 
					".job p { margin: 0.75em 0 3em 0; }\n" + 
					"\n" + 
					".last { border: none; }\n" + 
					".skills-list {  }\n" + 
					".skills-list ul { margin: 0; }\n" + 
					".skills-list li { margin: 3px 0; padding: 3px 0; }\n" + 
					".skills-list li span { font-size: 152%; display: block; margin-bottom: -2px; padding: 0 }\n" + 
					".talent { width: 32%; float: left }\n" + 
					".talent h2 { margin-bottom: 6px; }\n" + 
					"\n" + 
					"#srt-ttab { margin-bottom: 100px; text-align: center;  }\n" + 
					"#srt-ttab img.last { margin-top: 20px }\n" + 
					"\n" + 
					"/* --// override to force 1/8th width grids -- */\n" + 
					".yui-gf .yui-u{width:80.2%;}\n" + 
					".yui-gf div.first{width:12.3%;}\n" + 
					"\n" + 
					"\n" + 
					"");
			
			bw.close();
			
		} catch (FileNotFoundException	nf) {
			System.out.println("File not found.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
