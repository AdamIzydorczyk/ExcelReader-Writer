package tk.aizydorczyk.excel.writer.cells;

import tk.aizydorczyk.excel.common.model.DataBlock;
import tk.aizydorczyk.excel.common.model.Header;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import static tk.aizydorczyk.excel.common.messages.Messages.CANNOT_GET_VALUE_FROM_FIELD;
import static tk.aizydorczyk.excel.common.utility.WriterHelper.getObjectFromFieldOrThrow;

final class SingleDataBlockFromComplexObjectStrategy implements BlocksCreationStrategy {

	@Override
	public List<DataBlock> createBlocks(BlocksCreationDto creationDto, BiFunction<Object, List<Header>, DataBlock> blockCreationFunction) {
		final Optional<Object> optional = getObjectFromFieldOrThrow(creationDto.field, creationDto.untypedObject, () ->
				new DataBlockCreator.DataBlockCreateFail(CANNOT_GET_VALUE_FROM_FIELD));

		return optional.map(data ->
				Collections.singletonList(blockCreationFunction.apply(data, creationDto.header.getBottomHeaders())))
				.orElseGet(() ->
						Collections.singletonList(blockCreationFunction.apply(null, creationDto.header.getBottomHeaders()))
				);
	}
}