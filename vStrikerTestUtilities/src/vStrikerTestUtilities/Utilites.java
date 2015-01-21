package vStrikerTestUtilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import vStrikerBizModel.ExecutionReportBiz;
import vStrikerBizModel.ExecutionReportDataBiz;
import vStrikerEntities.ExecutionReport;
import vStrikerEntities.ExecutionReportData;

public class Utilites {

	public static List<String> generateFiles(String testConfName, int objSize,
			int numfiles) throws IOException {
		List<String> list = new ArrayList<String>();
		String workingDir = Paths.get("").toAbsolutePath().toString();
		File dir = new File(workingDir + "/StageFiles");
		if (!dir.exists())
			dir.mkdir();
		String fileName = dir +  "/" + testConfName + "_1.txt";
		String fname = dir + "/" + testConfName + "_";
		int i = 2;

		RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		try {
			raf.setLength(objSize);
		} finally {
			raf.close();
		}
		list.add(fileName);
		File src = new File(fileName);
		while (i <= numfiles) {
			String dest = fname + i + ".txt";
			File ds = new File(dest);
			FileUtils.copyFile(src, ds);
			list.add(dest);
			i++;
		}
		return list;
	}

	public static List<String> generateTestFileList(String testConfName,
			int objSize, int numfiles) throws IOException {
		List<String> list = new ArrayList<String>();

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();

		File dir = new File(s.substring(0, s.lastIndexOf("\\") + 1)
				+ "StageFiles");
		if (!dir.exists()) {
			dir.mkdir();
		}

		String fileName = dir + "\\" + testConfName + "_1.txt";
		String fname = dir + "\\" + testConfName + "_";
		int i = 2;

		RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		try {
			raf.setLength(objSize);
		} finally {
			raf.close();
		}
		list.add(fileName);
		File src = new File(fileName);
		while (i <= numfiles) {
			String dest = fname + i + ".txt";
			File ds = new File(dest);

			FileUtils.copyFile(src, ds);
			list.add(dest);
			i++;

		}
		return list;

	}

	// File size code

	public void exportResultToFile(String filename, int executionRptID)
			throws Exception {

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();

		File dir = new File(s.substring(0, s.lastIndexOf("\\") + 1)
				+ "ResultFiles");
		if (!dir.exists()) {
			dir.mkdir();
		}

		ExecutionReport rpt = ExecutionReportBiz
				.ExecutionReportSelect(executionRptID);
		List<ExecutionReportData> list = ExecutionReportDataBiz
				.ExecutionReportDataSelectByRprtID(executionRptID);

		String sFileName = dir + "\\" + rpt.getExecutionName() + ".csv";
		FileWriter writer = new FileWriter(sFileName);

		writer.append("Execuation Name");
		writer.append(',');
		writer.append(rpt.getExecutionName());
		writer.append('\n');

		writer.append("Execuation Date");
		writer.append(',');
		writer.append(rpt.getExecutionDate() + "");
		writer.append('\n');

		writer.append("TOTAL_THROUGHPUT");
		writer.append(',');
		writer.append(rpt.getTotalThroughput());
		writer.append('\n');

		writer.append("MAX_THROUGHPUT");
		writer.append(',');
		writer.append(rpt.getMaxThroughput());
		writer.append('\n');
		writer.append("MIN_THROUGHPUT");
		writer.append(',');
		writer.append(rpt.getMinThroughput());
		writer.append('\n');
		writer.append("TOTAL_VOLUME_SENT");
		writer.append(',');
		writer.append(rpt.getTotalVolumeSent());
		writer.append('\n');
		writer.append("TOTAL_VOLUME_RECEIVED");
		writer.append(',');
		writer.append(rpt.getTotalVolumeReceived());
		writer.append('\n');
		writer.append("AVG_LATENCY_PER_CRUD_OPERATION");
		writer.append(',');
		writer.append(rpt.getAvgLatencyPerCrudOperation());
		writer.append('\n');
		writer.append("NUMBER_REQUEST_SEC");
		writer.append(',');
		writer.append(rpt.getNumberRequestSec() + "");
		writer.append('\n');
		writer.append("PERECENT_FAILED_REQUEST");
		writer.append(',');
		writer.append(rpt.getPerecentFailedRequest() + "");
		writer.append('\n');

		for (vStrikerEntities.ExecutionReportData d : list) {
			writer.append(d.getDataKey());
			writer.append(',');
			writer.append(d.getDataKey());
			writer.append('\n');

		}

		writer.flush();
		writer.close();
	}
}
