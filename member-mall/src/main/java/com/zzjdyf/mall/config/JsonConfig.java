package com.zzjdyf.mall.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.zzjdyf.common.constant.JsonConstant;
import com.zzjdyf.common.tools.utils.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Configuration
public class JsonConfig {
	
	@Bean(JsonConstant.OBJECT_MAPPER)
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();

		// 移除对 Date 类型的特殊类型处理
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

		// 设置日期格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		// LocalDateTime系列序列化和反序列化模块，继承自jsr310，我们在这里修改了日期格式
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class,
				new LocalDateTimeSerializer(DateTimeUtil.COMMON_DATE_TIME_FORMAT));
		javaTimeModule.addDeserializer(LocalDateTime.class,
				new LocalDateTimeDeserializer(DateTimeUtil.COMMON_DATE_TIME_FORMAT));
		// LocalDate的序列化与反序列化
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeUtil.COMMON_DATE_FORMAT));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeUtil.COMMON_DATE_FORMAT));
		// LocalTime的序列化与反序列化
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeUtil.COMMON_TIME_FORMAT));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeUtil.COMMON_TIME_FORMAT));

		// Date序列化和反序列化
		javaTimeModule.addSerializer(Date.class, new JsonSerializer<Date>() {
			@Override
			public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
					throws IOException {
				SimpleDateFormat formatter = new SimpleDateFormat(DateTimeUtil.COMMON_DATE_TIME_FORMAT_VALUE);
				String formattedDate = formatter.format(date);
				jsonGenerator.writeString(formattedDate);
			}
		});
		javaTimeModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
					throws IOException, JsonProcessingException {
				SimpleDateFormat format = new SimpleDateFormat(DateTimeUtil.COMMON_DATE_TIME_FORMAT_VALUE);
				String date = jsonParser.getText();
				try {
					return format.parse(date);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		});

		objectMapper.registerModule(javaTimeModule);
		
		// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
		//objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		
		return objectMapper;
	}
}
